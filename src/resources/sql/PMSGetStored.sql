select  tim.storage_type
	   ,case when
			tsi.status = 'A'
		then 'ø‚¥Êø…”√'
		end as stored_status
	   ,isnull(ta.status,'Œ¥‘§‘º') allo_status
	   ,tsi.location_id
	   ,tsi.item_number
	   ,tim.description
	   ,case when
	    tsi.status = 'A'
		then
			actual_qty
		else
			0
		end as actual_qty
	   ,isnull(ta.allocated_qty,0) as allocated_qty
	   ,case when
	    tl.type = 'A'
		then
			actual_qty
		else
			0
		end as unuplocation
	   ,tl.type
 from t_stored_item tsi
  inner join t_item_master tim
     on tsi.item_number = tim.item_number
   left join tbl_allocation ta
     on tsi.item_number = ta.item_number
	and tsi.location_id = ta.location_id
	and isnull(ta.status,'U') = 'U'
   left join t_location tl
     on tl.location_id = tsi.location_id
  where tl.type not in ('F','T','X','W','D')
	and tsi.status = 'A'
	and tim.client_code = ?
	and tsi.item_number like ?
