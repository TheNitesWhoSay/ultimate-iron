
INSERT INTO rs_clan_member(RANK_NAME) VALUES (UPPER('unknown')); SET @clanMemberId = LAST_INSERT_ID();
INSERT INTO rs_user_name (CLAN_MEMBER_ID, USER_NAME) SELECT @clanMemberId, 'userNameHere' FROM DUAL; SET @userId = LAST_INSERT_ID();
INSERT INTO user_sighting (SIGHTING_TS, RANK_NAME, USER_NAME_ID) SELECT '2111-11-11', UPPER('unknown'), @userId FROM DUAL;
INSERT INTO ar_user_name (CLAN_MEMBER_ID, USER_NAME) SELECT @clanMemberId, 'userNameHere' FROM DUAL; SET @arUserId = LAST_INSERT_ID();
INSERT INTO user_sighting (SIGHTING_TS, RANK_NAME, USER_NAME_ID) SELECT '2111-11-11', UPPER('unknown'), @arUserId FROM DUAL;
