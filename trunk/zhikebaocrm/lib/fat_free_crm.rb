# 
#------------------------------------------------------------------------------

require "fat_free_crm/version"
require "fat_free_crm/core_ext"
require "fat_free_crm/exceptions"
require "fat_free_crm/i18n"
require "fat_free_crm/permissions"
require "fat_free_crm/sortable"
require "fat_free_crm/tabs"
require "fat_free_crm/callback"
require "fat_free_crm/plugin"

      ActionView::Base.send(:include, FatFreeCRM::I18n)
ActionController::Base.send(:include, FatFreeCRM::I18n)

      ActionView::Base.send(:include, FatFreeCRM::Callback::Helper)
ActionController::Base.send(:include, FatFreeCRM::Callback::Helper)

    ActiveRecord::Base.send(:include, FatFreeCRM::Permissions)
    ActiveRecord::Base.send(:include, FatFreeCRM::Sortable)
