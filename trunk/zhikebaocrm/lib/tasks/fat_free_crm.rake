# .
#------------------------------------------------------------------------------
class Rake::Task
  def self.sanitize_and_execute(sql)
    sanitized = if Rails::VERSION::STRING < "2.3.5"
      ActiveRecord::Base.send(:sanitize_sql, sql)
    else # Rails 2.3.3 introduced extra "table_name" parameter.
      ActiveRecord::Base.send(:sanitize_sql, sql, nil)
    end
    ActiveRecord::Base.connection.execute(sanitized)
  end
end

namespace :crm do

  namespace :settings do
    desc "Load default application settings"
    task :load => :environment do
      plugin = ENV["PLUGIN"]
      yaml = RAILS_ROOT + (plugin ? "/vendor/plugins/#{plugin}" : "") + "/config/settings.yml"
      begin
        settings = YAML.load_file(yaml)
      rescue
        puts "Couldn't load #{yaml} configuration file."
        exit
      end

      # Truncate settings table if loading Fat Free CRM settings.
      ActiveRecord::Base.establish_connection(Rails.env)
      unless plugin
        if ActiveRecord::Base.connection.adapter_name.downcase == "sqlite"
          ActiveRecord::Base.connection.execute("DELETE FROM settings")
        else # mysql and postgres
          ActiveRecord::Base.connection.execute("TRUNCATE settings")
        end
      end

      settings.keys.each do |key|
        if plugin # Delete existing plugin setting if any (since we haven't truncated the whole table).
          sql = [ "DELETE FROM settings WHERE name = ?", key.to_s ]
          Rake::Task.sanitize_and_execute(sql)
        end
        sql = [ "INSERT INTO settings (name, default_value, created_at, updated_at) VALUES(?, ?, ?, ?)", key.to_s, Base64.encode64(Marshal.dump(settings[key])), Time.now, Time.now ]
        Rake::Task.sanitize_and_execute(sql)
      end
    end

    desc "Show current application settings"
    task :show => :environment do
      ActiveRecord::Base.establish_connection(Rails.env)
      names = ActiveRecord::Base.connection.select_values("SELECT name FROM settings ORDER BY name")
      names.each do |name|
        puts "\n#{name}:\n  #{Setting.send(name).inspect}"
      end
    end
  end

  desc "Prepare the database and load default application settings"
  task :setup => :environment do
    proceed = true
    if ActiveRecord::Migrator.current_version > 0
      puts "\nYour database is about to be reset, so if you choose to proceed all the existing data will be lost.\n\n"
      loop do
        print "Continue [yes/no]: "
        proceed = STDIN.gets.strip
        break unless proceed.blank?
      end
      proceed = (proceed =~ /y(?:es)*/i)
    end
    if proceed
      Rake::Task["db:migrate:reset"].invoke
      Rake::Task["crm:settings:load"].invoke
      Rake::Task["crm:setup:admin"].invoke
    end
  end

  namespace :setup do
    desc "Create admin user"
    task :admin => :environment do
      username, password, email = ENV["USERNAME"], ENV["PASSWORD"], ENV["EMAIL"]
      unless username && password && email
        puts "\nTo create the admin user you will be prompted to enter username, password,"
        puts "and email address. You might also specify the username of existing user.\n"
        loop do
          username ||= "system"
          print "\nUsername [#{username}]: "
          reply = STDIN.gets.strip
          username = reply unless reply.blank?

          password ||= "manager"
          print "Password [#{password}]: "
          echo = lambda { |toggle| return if RUBY_PLATFORM =~ /mswin/; system(toggle ? "stty echo && echo" : "stty -echo") }
          begin
            echo.call(false)
            reply = STDIN.gets.strip
            password = reply unless reply.blank?
          ensure
            echo.call(true)
          end

          loop do
            print "Email: "
            email = STDIN.gets.strip
            break unless email.blank?
          end

          puts "\nThe admin user will be created with the following credentials:\n\n"
          puts "  Username: #{username}"
          puts "  Password: #{'*' * password.length}"
          puts "     Email: #{email}\n\n"
          loop do
            print "Continue [yes/no/exit]: "
            reply = STDIN.gets.strip
            break unless reply.blank?
          end
          break if reply =~ /y(?:es)*/i
          redo if reply =~ /no*/i
          puts "No admin user was created."
          exit
        end
      end
      User.reset_column_information # Reload the class since we've added new fields in migrations.
      user = User.find_by_username(username) || User.new
      user.update_attributes(:username => username, :password => password, :email => email)
      user.update_attribute(:admin, true) # Mass assignments don't work for :admin because of the attr_protected
      puts "Admin user has been created."
    end
  end

  namespace :demo do
    desc "Load demo data and default application settings"
    task :load => :environment do
      Rake::Task["spec:db:fixtures:load"].invoke      # loading fixtures truncates settings!
      Rake::Task["crm:settings:load"].invoke

      # Simulate random user activities.
      $stdout.sync = true
      puts "Generating user activities..."
      %w(Account Campaign Contact Lead Opportunity Task).inject([]) do |assets, model|
        assets << model.constantize.send(:find, :all)
      end.flatten.shuffle.each do |subject|
        info = subject.respond_to?(:full_name) ? subject.full_name : subject.name
        Activity.create(:action => "created", :created_at => subject.updated_at, :user => subject.user, :subject => subject, :info => info)
        Activity.create(:action => "updated", :created_at => subject.updated_at, :user => subject.user, :subject => subject, :info => info)
        unless subject.is_a?(Task)
          time = subject.updated_at + rand(12 * 60).minutes
          Activity.create(:action => "viewed", :created_at => time, :user => subject.user, :subject => subject, :info => info)
          comments = Comment.find(:all, :conditions => [ "commentable_id=? AND commentable_type=?", subject.id, subject.class.name ])
          comments.each_with_index do |comment, i|
            time = subject.created_at + rand(12 * 60 * i).minutes
            if time > Time.now
              time = subject.created_at + rand(600).minutes
            end
            comment.update_attribute(:created_at, time)
            Activity.create(:action => "commented", :created_at => time, :user => comment.user, :subject => subject, :info => info)
          end
        end
        print "." if subject.id % 10 == 0
      end
      puts
    end

    desc "Reset the database and reload demo data along with default application settings"
    task :reload => :environment do
      Rake::Task["db:migrate:reset"].invoke
      Rake::Task["crm:demo:load"].invoke
    end
  end
end
