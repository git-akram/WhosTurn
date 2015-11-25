-- BlogUser
INSERT INTO User (id,username,createdate,lastUpdate) VALUES (1, 'toto', sysdate, sysdate);

INSERT INTO User (id,username,createdate,lastUpdate) VALUES (2, 'Bob', sysdate, sysdate);

-- BlogPost
INSERT INTO BlogPost (id, createDate, lastUpdate, postContent, title) VALUES (1,sysdate, sysdate, 'because I ride to Gas Town.', 'Why do I write so good posts ?');

INSERT INTO BlogPost_User (BlogPost_id, authors_id) VALUES (1, 1);

-- Tags
INSERT INTO BlogTag (id, createDate, lastUpdate, label) VALUES (1,sysdate, sysdate, 'The one truth.');
-- BlogPost_BlogTag
INSERT INTO BlogPost_BlogTag (BlogPost_id, tags_id) VALUES (1, 1);