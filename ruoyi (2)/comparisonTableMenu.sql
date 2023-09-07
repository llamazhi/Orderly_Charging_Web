-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985734822211586, '【请填写功能名称】', '3', '1', 'comparisonTable', '/comparisonTable/index', 1, 0, 'C', '0', '0', ':comparisonTable:list', '#', 'admin', sysdate(), '', null, '【请填写功能名称】菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985734822211587, '【请填写功能名称】查询', 1691985734822211586, '1',  '#', '', 1, 0, 'F', '0', '0', ':comparisonTable:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985734822211588, '【请填写功能名称】新增', 1691985734822211586, '2',  '#', '', 1, 0, 'F', '0', '0', ':comparisonTable:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985734822211589, '【请填写功能名称】修改', 1691985734822211586, '3',  '#', '', 1, 0, 'F', '0', '0', ':comparisonTable:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985734822211590, '【请填写功能名称】删除', 1691985734822211586, '4',  '#', '', 1, 0, 'F', '0', '0', ':comparisonTable:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values(1691985734822211591, '【请填写功能名称】导出', 1691985734822211586, '5',  '#', '', 1, 0, 'F', '0', '0', ':comparisonTable:export',       '#', 'admin', sysdate(), '', null, '');
