package db

import future.keywords.if
import future.keywords.in

default allow = false

allow if {
	input.query.tablename in data.dproducts_tables[input.dp]
}
request := input.request
query := input.query if allow
