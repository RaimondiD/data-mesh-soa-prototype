package medical
import future.keywords.if
import future.keywords.in
import future.keywords.every
default allow := false


roles_columns(x):= roles_data[x] if { roles_data :={"data scientist" : data.impersonal_columns ,
				"patient" : data.db_columns,
                "guarantor" : data.db_columns,
                "medic" : data.db_columns}
                }
roles_conditions(x) := condition_data[x] if {
					condition_data := {"medic" : [concat("",["medic_cf-equals-","'",input.user.cf,"'"])],
					"patient" : [concat("", ["cf-equals-","'",input.user.cf,"'"])] 
                    }
}
action_read(user):= true if user.action == "read"

cf_check(user) := true if{
    user.role != "medic"
    user.role != "patient"
}{
    user.cf
}



allow := true if{ 
    data.allowed_roles[_] == input.user.profession
    input.user.action == data.role_permission[input.user.profession][_]   # the action of the user is a action valid for that role
    action_read(input.user)                                           
    cf_check(input.user)
}{
   	data.allowed_roles[_] == input.user.profession
    input.user.action == data.role_permission[input.user.profession][_]   
    input.user.action == "delete"	
}

action_delete if input.user.action == "delete"
query_columns := roles_columns(input.user.profession) if action_read(input.user)
query_conditions := roles_conditions(input.user.profession) if action_read(input.user)
delete_columns = input.user.del_columns if {
	action_delete
    input.user.del_columns != data.key_column
    input.user.del_columns in data.db_columns
    
    }
delete_keys := input.user.del_cf if input.user.del_cf
cdc := true if input.user.cdc
user_id := input.user.user_id