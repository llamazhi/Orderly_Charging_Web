-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985726500712450, '【请填写功能名称】', '3', '1', 'timeComparisonTable', '/timeComparisonTable/index', 1, 0, 'C', '0', '0', ':timeComparisonTable:list', '#', 'admin', sysdate(), '', null, '【请填写功能名称】菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985726500712451, '【请填写功能名称】查询', 1691985726500712450, '1',  '#', '', 1, 0, 'F', '0', '0', ':timeComparisonTable:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985726500712452, '【请填写功能名称】新增', 1691985726500712450, '2',  '#', '', 1, 0, 'F', '0', '0', ':timeComparisonTable:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985726500712453, '【请填写功能名称】修改', 1691985726500712450, '3',  '#', '', 1, 0, 'F', '0', '0', ':timeComparisonTable:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985726500712454, '【请填写功能名称】删除', 1691985726500712450, '4',  '#', '', 1, 0, 'F', '0', '0', ':timeComparisonTable:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985726500712455, '【请填写功能名称】导出', 1691985726500712450, '5',  '#', '', 1, 0, 'F', '0', '0', ':timeComparisonTable:export',       '#', 'admin', sysdate(), '', null, '');
