INSERT INTO authority(id, permission) VALUES (1, 'user.create');
INSERT INTO authority(id, permission) VALUES (2, 'user.update');
INSERT INTO authority(id, permission) VALUES (3, 'user.delete');

INSERT INTO role(id, name) VALUES (1, 'ADMIN');
INSERT INTO role(id, name) VALUES (2, 'USER');

INSERT INTO role_authority(role_id, authority_id) VALUES (1, 1);
INSERT INTO role_authority(role_id, authority_id) VALUES (1, 2);
INSERT INTO role_authority(role_id, authority_id) VALUES (1, 3);

INSERT INTO role_authority(role_id, authority_id) VALUES (2, 1);
INSERT INTO role_authority(role_id, authority_id) VALUES (2, 2);












