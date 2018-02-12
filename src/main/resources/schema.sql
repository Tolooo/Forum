CREATE TABLE UserRole (
  id   IDENTITY,
  role VARCHAR2(30) NOT NULL
);
CREATE TABLE UserCredentials (
  id           IDENTITY,
  username     VARCHAR2(30) NOT NULL UNIQUE,
  email        VARCHAR2(30) NOT NULL,
  password     VARCHAR2(30) NOT NULL,
  enabled      BOOL DEFAULT TRUE,
  creationDate DATETIME
);
CREATE TABLE Category (
  id           IDENTITY,
  title        VARCHAR2(50)  NOT NULL,
  description  VARCHAR2(255) NOT NULL,
  creationDate DATETIME,
  user_id      BIGINT
);
CREATE TABLE Topic (
  id           IDENTITY,
  title        VARCHAR2(50)  NOT NULL,
  description  VARCHAR2(255) NOT NULL,
  creationDate DATETIME,
  user_id      BIGINT
);
CREATE TABLE Comment (
  id           IDENTITY,
  description  VARCHAR2(255) NOT NULL,
  creationDate DATETIME,
  user_id      BIGINT,
  FOREIGN KEY (user_id) REFERENCES UserCredentials (id)
);

CREATE TABLE UserCredentialsRoles (
  user_credentials_id BIGINT NOT NULL,
  roles_id            BIGINT NOT NULL
);
CREATE TABLE CategorySubCategories (
  category_id       BIGINT NOT NULL,
  sub_categories_id BIGINT NOT NULL
);
CREATE TABLE CategoryTopics (
  category_id BIGINT NOT NULL,
  topics_id   BIGINT NOT NULL
);
CREATE TABLE TopicComments (
  topic_id    BIGINT NOT NULL,
  comments_id BIGINT NOT NULL
);
ALTER TABLE UserCredentialsRoles
  ADD CONSTRAINT fk_role FOREIGN KEY (roles_id) REFERENCES UserRole;
ALTER TABLE UserCredentialsRoles
  ADD CONSTRAINT fk_user FOREIGN KEY (user_credentials_id) REFERENCES UserCredentials;

ALTER TABLE CategorySubCategories
  ADD CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES Category;
ALTER TABLE CategorySubCategories
  ADD CONSTRAINT fk_sub_categories FOREIGN KEY (sub_categories_id) REFERENCES Category;

ALTER TABLE CategoryTopics
  ADD CONSTRAINT fk_category_topic FOREIGN KEY (category_id) REFERENCES Category;
ALTER TABLE CategoryTopics
  ADD CONSTRAINT fk_topics FOREIGN KEY (topics_id) REFERENCES Topic;

ALTER TABLE TopicComments
  ADD CONSTRAINT fk_topic FOREIGN KEY (topic_id) REFERENCES Topic;
ALTER TABLE TopicComments
  ADD CONSTRAINT fk_comments FOREIGN KEY (comments_id) REFERENCES Comment;

INSERT INTO UserCredentials VALUES (1, 'admin', 'a@aa.pl', 123123, TRUE, '2018-02-12 12:00:00');
INSERT INTO UserRole VALUES (1, 'ROLE_ADMIN');
INSERT INTO UserRole VALUES (2, 'ROLE_ACTUATOR');
INSERT INTO UserCredentialsRoles VALUES (1, 1);
INSERT INTO UserCredentialsRoles VALUES (1, 2);

INSERT INTO Category VALUES (1, 'News, Rules and Feedback', '', '2018-02-12 12:00:00',1);
INSERT INTO Category VALUES (2, 'Forum rules and warnings',
                             'These forums have a set of rules as well as terms and conditions that all members are expected to abide by when posting here. You can find out more about our rules here.',
                             '2018-02-12 12:00:00',1);
INSERT INTO CategorySubCategories VALUES (1, 2);
INSERT INTO Topic VALUES (1, ' Guidelines for reportings, users, files, etc. ',
                          'Lorem ipsum dolor sit amet interdum pellentesque quis, ipsum. Nam nec nunc posuere eget, faucibus orci pellentesque sed, rutrum consectetuer tortor. Vivamus hendrerit sollicitudin. Cras.',
                          '2018-02-12 12:00:00',1);
INSERT INTO CategoryTopics VALUES (2, 1);
INSERT INTO Comment VALUES (1,
                            'Lorem ipsum dolor sit amet interdum pellentesque quis, ipsum. Nam nec nunc posuere eget, faucibus orci pellentesque sed, rutrum consectetuer tortor. Vivamus hendrerit sollicitudin. Cras.',
                            '2018-02-12 12:00:00',
                            1);
INSERT INTO TopicComments VALUES (1, 1);

INSERT INTO Category VALUES (3, 'Discussions', '', '2018-02-12 12:00:00',1);
INSERT INTO Category
VALUES (4, 'Articles', 'Articles of interest found or written by members of this site for others to discuss.',
        '2018-02-12 12:00:00',1);
INSERT INTO CategorySubCategories VALUES (1, 4);
INSERT INTO Topic VALUES (2, 'Lorem ipsum dolor sit.',
                          'Lorem ipsum dolor sit amet interdum pellentesque quis, ipsum. Nam nec nunc posuere eget, faucibus orci pellentesque sed, rutrum consectetuer tortor. Vivamus hendrerit sollicitudin. Cras.',
                          '2018-02-13 12:00:00',1);
INSERT INTO CategoryTopics VALUES (4, 2);
INSERT INTO Comment
VALUES (2, 'Lorem ipsum dolor sit amet dui gravida mattis, magna iaculis augue. Donec suscipit wisi. Phasellus.',
        '2018-02-12 12:00:00', 1);
INSERT INTO TopicComments VALUES (2, 2);
