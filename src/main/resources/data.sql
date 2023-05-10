INSERT INTO `person` (
    `id`,
    `address`,
    `dni_number`,
    `id_dni_type`,
    `id_person_type`,
    `mail`,
    `name`,
    `password`,
    `phone`,
    `surname`,
    `token_password`
  )
VALUES
  (
    '1',
    'st 123 # 456',
    '123',
    '1',
    '1',
    'email@some.com',
    'Name',
    '$2a$10$GlsGSNhkbVon6ZOSNMptOu5RikedRzlCAhMa7YpwvUSS0c88WT99S',
    '1234567890',
    'Surname',
    NULL
  );

INSERT INTO `person` (
    `id`,
    `address`,
    `birth_date`,
    `dni_number`,
    `id_dni_type`,
    `id_person_type`,
    `mail`,
    `name`,
    `password`,
    `phone`,
    `surname`,
    `token_password`
)
VALUES
  (
    '2',
    'st 123 # 456',
    '01-01-2022',
    '1234',
    '1',
    '3',
    'mau5@mau5.com',
    'Mau5',
    '$2a$10$7CMLZzXnikN6JUqK5h3wRutvRO8/XkCR.B88VDnVMKf629WGY7Rj2',
    '123456789',
    'Mau5',
    NULL
    );


INSERT INTO `role` (`id`, `description`, `name`) VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('2', 'ROLE_OWNER', 'ROLE_OWNER');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('3', 'ROLE_USER', 'ROLE_USER');

INSERT INTO `user` (`id_person`, `id_role`) VALUES ('1', '1');