
module Admin::ApplicationHelper

  def tabs
    super(FatFreeCRM::Tabs.admin)
  end

  #----------------------------------------------------------------------------
  def link_to_edit(model)
    name = model.class.name.downcase
    link_to_remote(t(:edit),
      :method => :get,
      :url    => send("edit_admin_#{name}_path", model),
      :with   => "{ previous: crm.find_form('edit_#{name}') }"
    )
  end

end
