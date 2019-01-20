INSERT INTO sbs.t_role (id, name) VALUES (1, '系统管理员');
INSERT INTO sbs.t_role (id, name) VALUES (2, '普通用户');

INSERT INTO sbs.t_user (id, name, password, username, role_id) VALUES (1, 'tom', '93c3ef4bc1b01b865028192eeda1a41d', 'tom', 1);
INSERT INTO sbs.t_user (id, name, password, username, role_id) VALUES (2, 'mike', '9d6c82f7650bdf2dd881134db45d1b41', 'mike', 2);

INSERT INTO sbs.t_node (id, text, url, parent_id) VALUES (1, '基础资料', null, null);
INSERT INTO sbs.t_node (id, text, url, parent_id) VALUES (2, '菜单管理', '/node/toMenu', 1);
INSERT INTO sbs.t_node (id, text, url, parent_id) VALUES (3, '用户管理', '/user/toList', 1);
INSERT INTO sbs.t_node (id, text, url, parent_id) VALUES (4, '角色管理', '/role/toList', 1);
INSERT INTO sbs.t_node (id, text, url, parent_id) VALUES (5, '权限管理', '', null);
INSERT INTO sbs.t_node (id, text, url, parent_id) VALUES (6, '角色授菜单', '/permission/toRoleNodes', 5);
INSERT INTO sbs.t_node (id, text, url, parent_id) VALUES (7, '用户授角色', '/permission/toUserRole', 5);

INSERT INTO sbs.role_node (role_id, node_id) VALUES (1, 1);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (2, 1);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (1, 2);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (2, 2);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (1, 3);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (2, 3);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (1, 4);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (2, 4);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (1, 5);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (1, 6);
INSERT INTO sbs.role_node (role_id, node_id) VALUES (1, 7);

