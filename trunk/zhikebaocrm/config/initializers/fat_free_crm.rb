require "fat_free_crm"

#---------------------------------------------------------------------
Sass::Plugin.options[:template_location] = File.join(RAILS_ROOT, "app/stylesheets")
Sass::Plugin.options[:css_location] = File.join(RAILS_ROOT, "public/stylesheets")

#---------------------------------------------------------------------
Date::DATE_FORMATS[:mmddyyyy] = "%m/%d/%Y"
Date::DATE_FORMATS[:mmdd] = "%b %e"
Date::DATE_FORMATS[:mmddyy] = "%b %e, %Y"
Time::DATE_FORMATS[:mmddhhss] = "%m/%d %H:%M"

#---------------------------------------------------------------------
WillPaginate::ViewHelpers.pagination_options[:renderer] = "AjaxWillPaginate"
