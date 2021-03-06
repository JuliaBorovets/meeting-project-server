DROP SCHEMA IF EXISTS `meeting_project_test`;
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `meeting_project_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `meeting_project_test`;

-- -----------------------------------------------------
-- Table `meeting_project_test`.`authority`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`authority`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME     NULL DEFAULT NULL,
    `permission`    VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project`.`location`

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`location`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME     NULL DEFAULT NULL,
    `city`          VARCHAR(255) NULL DEFAULT NULL,
    `country`       VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`organisation`

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`organisation`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME     NULL DEFAULT NULL,
    `info`          VARCHAR(255) NULL DEFAULT NULL,
    `is_private`    BIT(1)       NULL DEFAULT NULL,
    `name`          VARCHAR(255) NULL DEFAULT NULL,
    `photo`         LONGBLOB     NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`event`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`event`
(
    `id`                      BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date`           DATETIME     NULL DEFAULT NULL,
    `begin_date`              DATE         NULL DEFAULT NULL,
    `end_date`                DATE         NULL DEFAULT NULL,
    `event_type`              VARCHAR(255) NULL DEFAULT NULL,
    `max_number_participants` BIGINT       NULL DEFAULT NULL,
    `name`                    VARCHAR(255) NULL DEFAULT NULL,
    `photo`                   LONGBLOB     NULL DEFAULT NULL,
    `location_id`             BIGINT       NULL DEFAULT NULL,
    `organisation_id`         BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKbb6c0h5nhs5og47iem617ehrl` (`location_id` ASC),
    INDEX `FKt7kl5umnufdte3xmqq01vkls` (`organisation_id` ASC),
    CONSTRAINT `FKbb6c0h5nhs5og47iem617ehrl`
        FOREIGN KEY (`location_id`)
            REFERENCES `meeting_project_test`.`location` (`id`),
    CONSTRAINT `FKt7kl5umnufdte3xmqq01vkls`
        FOREIGN KEY (`organisation_id`)
            REFERENCES `meeting_project_test`.`organisation` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`user_profile`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`user_profile`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME     NULL DEFAULT CURRENT_TIMESTAMP,
    `bio`           VARCHAR(255) NULL DEFAULT NULL,
    `birthday`      DATE         NULL DEFAULT NULL,
    `first_name`    VARCHAR(255) NULL DEFAULT NULL,
    `image`         LONGBLOB     NULL DEFAULT NULL,
    `last_name`     VARCHAR(255) NULL DEFAULT NULL,
    `phone_number`  VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`user`
(
    `id`                      BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date`           DATETIME     NULL DEFAULT CURRENT_TIMESTAMP,
    `account_non_expired`     BIT(1)       NULL DEFAULT 1,
    `account_non_locked`      BIT(1)       NULL DEFAULT 1,
    `credentials_non_expired` BIT(1)       NULL DEFAULT 1,
    `email`                   VARCHAR(255) NULL DEFAULT NULL,
    `enabled`                 BIT(1)       NULL DEFAULT 1,
    `google2fa_secret`        VARCHAR(255) NULL DEFAULT NULL,
    `last_modified_date`      DATETIME     NULL DEFAULT CURRENT_TIMESTAMP,
    `password`                VARCHAR(255) NULL DEFAULT NULL,
    `user_google2fa`          BIT(1)       NULL DEFAULT 0,
    `username`                VARCHAR(255) NULL DEFAULT NULL,
    `location_id`             BIGINT       NULL DEFAULT NULL,
    `profile_id`              BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe` (`email` ASC),
    UNIQUE INDEX `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username` ASC),
    INDEX `FKneyhvoj17hax43m8dq3u7gbic` (`location_id` ASC),
    INDEX `FK5hv52mjjufrwrx302p37tq262` (`profile_id` ASC),
    CONSTRAINT `FK5hv52mjjufrwrx302p37tq262`
        FOREIGN KEY (`profile_id`)
            REFERENCES `meeting_project_test`.`user_profile` (`id`),
    CONSTRAINT `FKneyhvoj17hax43m8dq3u7gbic`
        FOREIGN KEY (`location_id`)
            REFERENCES `meeting_project_test`.`location` (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 4
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`event_comment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`event_comment`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME     NULL DEFAULT NULL,
    `text`          VARCHAR(255) NULL DEFAULT NULL,
    `user_id`       BIGINT       NULL DEFAULT NULL,
    `event_id`      BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKa7frm721972d53jrsw9vklqio` (`user_id` ASC),
    INDEX `FKs4ojbhvimbvt7758uvlx8ddlo` (`event_id` ASC),
    CONSTRAINT `FKa7frm721972d53jrsw9vklqio`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`),
    CONSTRAINT `FKs4ojbhvimbvt7758uvlx8ddlo`
        FOREIGN KEY (`event_id`)
            REFERENCES `meeting_project_test`.`event` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`rating`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`rating`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME     NULL DEFAULT NULL,
    `name`          VARCHAR(255) NULL DEFAULT NULL,
    `score`         INT          NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`event_reaction`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`event_reaction`
(
    `id`            BIGINT   NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME NULL DEFAULT NULL,
    `rating_id`     BIGINT   NULL DEFAULT NULL,
    `user_id`       BIGINT   NULL DEFAULT NULL,
    `event_id`      BIGINT   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FK4pul5a4mb2sot4ly0afmm99dt` (`rating_id` ASC),
    INDEX `FK8ivkj9brky6rikjjqq8mbu1m3` (`user_id` ASC),
    INDEX `FK2j7fi0ncp21fn7e6n77ky4iw3` (`event_id` ASC),
    CONSTRAINT `FK2j7fi0ncp21fn7e6n77ky4iw3`
        FOREIGN KEY (`event_id`)
            REFERENCES `meeting_project_test`.`event` (`id`),
    CONSTRAINT `FK4pul5a4mb2sot4ly0afmm99dt`
        FOREIGN KEY (`rating_id`)
            REFERENCES `meeting_project_test`.`rating` (`id`),
    CONSTRAINT `FK8ivkj9brky6rikjjqq8mbu1m3`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`login_failure`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`login_failure`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date`      DATETIME     NULL DEFAULT NULL,
    `last_modified_date` DATETIME     NULL DEFAULT NULL,
    `source_ip`          VARCHAR(255) NULL DEFAULT NULL,
    `username`           VARCHAR(255) NULL DEFAULT NULL,
    `user_id`            BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FK7yuqycsl6io9aivn03yr2hiia` (`user_id` ASC),
    CONSTRAINT `FK7yuqycsl6io9aivn03yr2hiia`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`login_success`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`login_success`
(
    `id`                 BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date`      DATETIME     NULL DEFAULT NULL,
    `last_modified_date` DATETIME     NULL DEFAULT NULL,
    `source_ip`          VARCHAR(255) NULL DEFAULT NULL,
    `user_id`            BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKbhp83p080sodt4vrtcgbsdl9e` (`user_id` ASC),
    CONSTRAINT `FKbhp83p080sodt4vrtcgbsdl9e`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`notification`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`notification`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME     NULL DEFAULT NULL,
    `is_read`       BIT(1)       NULL DEFAULT NULL,
    `text`          VARCHAR(255) NULL DEFAULT NULL,
    `user_id`       BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FKb0yvoep4h4k92ipon31wmdf7e` (`user_id` ASC),
    CONSTRAINT `FKb0yvoep4h4k92ipon31wmdf7e`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`organisation_comment`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`organisation_comment`
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date`   DATETIME     NULL DEFAULT NULL,
    `text`            VARCHAR(255) NULL DEFAULT NULL,
    `user_id`         BIGINT       NULL DEFAULT NULL,
    `organisation_id` BIGINT       NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FK5fw9q8vrot721oxwek882pb35` (`user_id` ASC),
    INDEX `FK43yb9gtdfbuwp9o2j4pm8p5qg` (`organisation_id` ASC),
    CONSTRAINT `FK43yb9gtdfbuwp9o2j4pm8p5qg`
        FOREIGN KEY (`organisation_id`)
            REFERENCES `meeting_project_test`.`organisation` (`id`),
    CONSTRAINT `FK5fw9q8vrot721oxwek882pb35`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`organisation_reaction`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`organisation_reaction`
(
    `id`              BIGINT   NOT NULL AUTO_INCREMENT,
    `creation_date`   DATETIME NULL DEFAULT NULL,
    `rating_id`       BIGINT   NULL DEFAULT NULL,
    `user_id`         BIGINT   NULL DEFAULT NULL,
    `organisation_id` BIGINT   NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    INDEX `FK9v67gpi1ocgkiqi1dxxu37f` (`rating_id` ASC),
    INDEX `FK8ov0xde2txep8hu2bc9x88wcj` (`user_id` ASC),
    INDEX `FKlggckwo0e77l28mejyo51gvu4` (`organisation_id` ASC),
    CONSTRAINT `FK8ov0xde2txep8hu2bc9x88wcj`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`),
    CONSTRAINT `FK9v67gpi1ocgkiqi1dxxu37f`
        FOREIGN KEY (`rating_id`)
            REFERENCES `meeting_project_test`.`rating` (`id`),
    CONSTRAINT `FKlggckwo0e77l28mejyo51gvu4`
        FOREIGN KEY (`organisation_id`)
            REFERENCES `meeting_project_test`.`organisation` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`organizer_organisation`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`organizer_organisation`
(
    `user_id`         BIGINT NOT NULL,
    `organisation_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `organisation_id`),
    INDEX `FKi6btsbjlsshbiipax0co7rdab` (`organisation_id` ASC),
    CONSTRAINT `FKbksslw09c6chjik3yfrcggt0y`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`),
    CONSTRAINT `FKi6btsbjlsshbiipax0co7rdab`
        FOREIGN KEY (`organisation_id`)
            REFERENCES `meeting_project_test`.`organisation` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`participant_organisation`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`participant_organisation`
(
    `user_id`         BIGINT NOT NULL,
    `organisation_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `organisation_id`),
    INDEX `FKjrf5ptc2jis9hswr1j8tqo5ob` (`organisation_id` ASC),
    CONSTRAINT `FKjrf5ptc2jis9hswr1j8tqo5ob`
        FOREIGN KEY (`organisation_id`)
            REFERENCES `meeting_project_test`.`organisation` (`id`),
    CONSTRAINT `FKk7gb0mttp8ycqtgxoybcwo8o6`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`persistent_logins`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`persistent_logins`
(
    `username`  VARCHAR(64) NOT NULL,
    `series`    VARCHAR(64) NOT NULL,
    `token`     VARCHAR(64) NOT NULL,
    `last_used` TIMESTAMP   NOT NULL,
    PRIMARY KEY (`series`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`role`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`role`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME     NULL DEFAULT NULL,
    `name`          VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`role_authority`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`role_authority`
(
    `role_id`      BIGINT NOT NULL,
    `authority_id` BIGINT NOT NULL,
    PRIMARY KEY (`role_id`, `authority_id`),
    INDEX `FKqbri833f7xop13bvdje3xxtnw` (`authority_id` ASC),
    CONSTRAINT `FK2052966dco7y9f97s1a824bj1`
        FOREIGN KEY (`role_id`)
            REFERENCES `meeting_project_test`.`role` (`id`),
    CONSTRAINT `FKqbri833f7xop13bvdje3xxtnw`
        FOREIGN KEY (`authority_id`)
            REFERENCES `meeting_project_test`.`authority` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`tag`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`tag`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT,
    `creation_date` DATETIME     NULL DEFAULT NULL,
    `name`          VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`tag_event`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`tag_event`
(
    `tag_id`   BIGINT NOT NULL,
    `event_id` BIGINT NOT NULL,
    INDEX `FK26pkm7j50lbwxhafk844t7f52` (`event_id` ASC),
    INDEX `FKpqyke3bxjl4ftb6txeeqnarbv` (`tag_id` ASC),
    CONSTRAINT `FK26pkm7j50lbwxhafk844t7f52`
        FOREIGN KEY (`event_id`)
            REFERENCES `meeting_project_test`.`event` (`id`),
    CONSTRAINT `FKpqyke3bxjl4ftb6txeeqnarbv`
        FOREIGN KEY (`tag_id`)
            REFERENCES `meeting_project_test`.`tag` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`user_role`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`user_role`
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`),
    INDEX `FKa68196081fvovjhkek5m97n3y` (`role_id` ASC),
    CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`),
    CONSTRAINT `FKa68196081fvovjhkek5m97n3y`
        FOREIGN KEY (`role_id`)
            REFERENCES `meeting_project_test`.`role` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `meeting_project_test`.`user_tag`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `meeting_project_test`.`user_tag`
(
    `user_id` BIGINT NOT NULL,
    `tag_id`  BIGINT NOT NULL,
    PRIMARY KEY (`user_id`, `tag_id`),
    INDEX `FK9qknt3y115f17660k0qnm9x3g` (`tag_id` ASC),
    CONSTRAINT `FK9qknt3y115f17660k0qnm9x3g`
        FOREIGN KEY (`tag_id`)
            REFERENCES `meeting_project_test`.`tag` (`id`),
    CONSTRAINT `FKhqbypqh9kyjp3jcslfg67c6n5`
        FOREIGN KEY (`user_id`)
            REFERENCES `meeting_project_test`.`user` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_general_ci;


