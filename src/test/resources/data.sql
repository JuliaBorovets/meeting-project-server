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

INSERT INTO user_profile(id) VALUES (1);
INSERT INTO user_profile(id) VALUES (2);
INSERT INTO user_profile(id) VALUES (3);

INSERT INTO user(id, email, password, username, profile_id)
VALUES (1, 'user@gmail.com', '$2a$10$NpHWcrcJIre4NdHow0G4Nuo19ZkMNw5BHd/2JwtJUrevcxvJ3dSw2', 'user', 1);

INSERT INTO user(id, email, password, username, profile_id)
VALUES (2, 'user2@gmail.com', '$2a$10$NpHWcrcJIre4NdHow0G4Nuo19ZkMNw5BHd/2JwtJUrevcxvJ3dSw2', 'user2', 2);

INSERT INTO user(id, email, password, username, profile_id)
VALUES (3, 'admin@gmail.com', '$2a$10$NpHWcrcJIre4NdHow0G4Nuo19ZkMNw5BHd/2JwtJUrevcxvJ3dSw2', 'admin', 3);

INSERT INTO user_role(user_id, role_id) VALUES (1, 2);
INSERT INTO user_role(user_id, role_id) VALUES (2, 2);
INSERT INTO user_role(user_id, role_id) VALUES (3, 1);
