
class HomeController < ApplicationController
  before_filter :require_user, :except => [ :toggle, :timezone ]
  before_filter "set_current_tab('/')", :only => :index
  before_filter "hook(:home_before_filter, self, :amazing => true)"

  #首页
  def index
    @hello = "Hello world" # The hook below can access controller's instance variables.
    hook(:home_controller, self, :params => "it works!")

    @activities = get_activities
  end

  # GET /home/options                                                      AJAX
  #----------------------------------------------------------------------------
  def options
    unless params[:cancel] == "true"
      @asset = @current_user.pref[:activity_asset] || "all"
      @user = @current_user.pref[:activity_user] || "all users"
      @duration = @current_user.pref[:activity_duration] || "two_days"
    end
  end

  # POST /home/redraw                                                      AJAX
  #----------------------------------------------------------------------------
  def redraw
    @current_user.pref[:activity_asset] = params[:asset] if params[:asset]
    @current_user.pref[:activity_user] = params[:user] if params[:user]
    @current_user.pref[:activity_duration] = params[:duration] if params[:duration]

    @activities = get_activities
    render :action => "index"
  end
  
  # GET /home/toggle                                                       AJAX
  #----------------------------------------------------------------------------
  def toggle
    if session[params[:id].to_sym]
      session.delete(params[:id].to_sym)
    else
      session[params[:id].to_sym] = true
    end
    render :nothing => true
  end

  # GET /home/timezone                                                     AJAX
  #----------------------------------------------------------------------------
  def timezone
    #
    # (new Date()).getTimezoneOffset() in JavaScript returns (UTC - localtime) in
    # minutes, while ActiveSupport::TimeZone expects (localtime - UTC) in seconds.
    #
    if params[:offset]
      session[:timezone_offset] = params[:offset].to_i * -60
      ActiveSupport::TimeZone[session[:timezone_offset]]
    end
    render :nothing => true
  end

  private
  #----------------------------------------------------------------------------
  def get_activities(options = {})
    options[:asset] ||= activity_asset
    options[:user] ||= activity_user
    options[:duration] ||= activity_duration

    Activity.latest(options).except(:viewed).visible_to(@current_user)
  end

  #----------------------------------------------------------------------------
  def activity_asset
    asset = @current_user.pref[:activity_asset]
    if asset.nil? || asset == "all"
      nil
    else
      asset.singularize.capitalize
    end
  end

  #----------------------------------------------------------------------------
  def activity_user
    user = @current_user.pref[:activity_user]
    if user && user != "all users"
      user = if user =~ /\s/  # first_name last_name
        User.first(:conditions => [ "first_name = ? AND last_name = ?" ] + user.split)
      elsif user =~ /@/ # email
        User.first(:conditions => [ "email = ?", user ])
      end
    end
    user.is_a?(User) ? user.id : nil
  end

  #----------------------------------------------------------------------------
  def activity_duration
    duration = @current_user.pref[:activity_duration]
    if duration
      words = duration.split("_") # "two_weeks" => 2.weeks
      if %w(one two).include?(words.first)
        %w(zero one two).index(words.first).send(words.last)
      end
    end
  end

end
