#!/bin/bash



_s_url="http://localhost:8080/stubu/api";
_s_cookiefile="/tmp/playground/stubu.cookie";

alias s_curl='curl -b '${_s_cookiefile}' -H"Accept:application/json" -H"Content-type:application/json"'

s_login ()
{
    local username="${1}";
    local password="${2}";
    curl -c ${_s_cookiefile} -H"Accept:application/json" -H"Content-type:application/json" -s ${_s_url}'/user/login?username='${username}'&password='${password} -X POST
}

s_assignAllPermissiontToRole() {
	local role="${1:?Role nor given}"
	
	for resource in LECTURE LECTURER TEST ASSIGNMENT BUILDING BUILDING_SECTION ADDRESS FLOOR ROOM FLOOR_MAP USER PERSON STUDENT STUDENT_GROUP GUEST DISCIPLINE COURSE SCHOOL SCHOOL_DEPARTMENT PERSONNEL NOTIFICATION SYSTEM SYSTEM_SETTING SYSTEM_PWREQ ROLE_PERMISSIONS ; do 
		for action in CREATE DELETE GET MODIFY GET_ALL SEARCH PURGE MODERATE ; do  
			s_curl ${_s_url}'/admin/role/permission/'${role}'/'${resource}'/'${action}'/0?strict=false' -X POST; 
			echo; 
		done ; 
	done
}

s_refreshPermissions() {
    s_curl -s ${_s_url}'/admin/role/permission/refresh' -X POST
}


