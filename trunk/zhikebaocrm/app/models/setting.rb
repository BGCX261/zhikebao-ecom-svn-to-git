# 
#------------------------------------------------------------------------------

# == Schema Information
# Schema version: 23
#
# Table name: settings
#
#  id            :integer(4)      not null, primary key
#  name          :string(32)      default(""), not null
#  value         :text
#  default_value :text
#  created_at    :datetime
#  updated_at    :datetime
#
class Setting < ActiveRecord::Base
  
  #-------------------------------------------------------------------
  def self.method_missing(method, *args)
    begin
      super(method, *args)
    rescue NoMethodError
      method_name = method.to_s
      if method_name.last == "="
        self[method_name.sub("=", "")] = args.first
      else
        self[method_name]
      end
    end
  end

  #-------------------------------------------------------------------
  def self.[] (name)
    setting = self.find_by_name(name.to_s)
    setting ? Marshal.load(Base64.decode64(setting.value || setting.default_value)) : nil
  end

  #-------------------------------------------------------------------
  def self.[]= (name, value)
    setting = self.find_by_name(name.to_s) || self.new(:name => name.to_s)
    setting.value = Base64.encode64(Marshal.dump(value))
    setting.save
  end

  # Unrolls [ :one, :two ] settings array into [[ "One", :one ], [ "Two", :two ]]
  # picking symbol translations from locale. If setting is not a symbol but
  # string it gets copied without translation.
  #-------------------------------------------------------------------
  def self.unroll(setting)
    send(setting).map { |key| [ key.is_a?(Symbol) ? I18n.t(key) : key, key.to_sym ] }
  end

end
