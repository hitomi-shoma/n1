--rolesテーブル--
INSERT IGNORE INTO roles(id, name) VALUES(1, "ROLE_GUEST");
INSERT IGNORE INTO roles(id, name) VALUES(2, "ROLE_GENERAL");
INSERT IGNORE INTO roles(id, name) VALUES(3, "ROLE_VIP");
INSERT IGNORE INTO roles(id, name) VALUES(4, "ROLE_ADMIN");

--usersテーブル--
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (1, '侍 太郎', 'サムライ タロウ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'taro.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (2, '侍 花子', 'サムライ ハナコ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'hanako.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true); 
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (3, '侍 義勝', 'サムライ ヨシカツ', '638-0644', '奈良県五條市西吉野町湯川X-XX-XX', '090-1234-5678', 'yoshikatsu.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (4, '侍 幸子', 'サムライ サチコ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'sachiko.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (5, '侍 幸', 'サムライ サチ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'sachi.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (6, '侍 美子', 'サムライ ミコ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'miko.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (7, '侍 良子', 'サムライ ヨシコ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'yoshiko.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (8, '侍 知美', 'サムライ トモミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'tomomi.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (9, '侍 亜美', 'サムライ アミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'ami.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (10, '侍 望', 'サムライ ノゾミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'nozomi.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (11, '侍 愛子', 'サムライ アイコ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'aiko.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (12, '侍 愛', 'サムライ アイ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'ai.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (13, '侍 時子', 'サムライ トキコ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'tokiko.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);
INSERT IGNORE INTO users(id, name, kana, post_code, address, phone_number, email, password, role_id, enabled) VALUES (14, '侍 芳江', 'サムライ ヨシエ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'yoshie.samurai@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 4, true);

-- categoriesテーブル --
INSERT IGNORE INTO categories (id, category_name) VALUES (1, 'あんかけスパゲッティー');
INSERT IGNORE INTO categories (id, category_name) VALUES (2, 'コーヒーぜんざい');
INSERT IGNORE INTO categories (id, category_name) VALUES (3, 'カレーうどん');
INSERT IGNORE INTO categories (id, category_name) VALUES (4, 'どて煮');
INSERT IGNORE INTO categories (id, category_name) VALUES (5, 'きしめん');
INSERT IGNORE INTO categories (id, category_name) VALUES (6, '味噌おでん');
INSERT IGNORE INTO categories (id, category_name) VALUES (7, '名古屋コーチン');
INSERT IGNORE INTO categories (id, category_name) VALUES (8, '天むす');
INSERT IGNORE INTO categories (id, category_name) VALUES (9, 'とんちゃん焼き');
INSERT IGNORE INTO categories (id, category_name) VALUES (10, '台湾まぜそば');
INSERT IGNORE INTO categories (id, category_name) VALUES (11, '手羽先');
INSERT IGNORE INTO categories (id, category_name) VALUES (12, 'ういろう');
INSERT IGNORE INTO categories (id, category_name) VALUES (13, 'モーニング');
INSERT IGNORE INTO categories (id, category_name) VALUES (14, 'エビフライ');
INSERT IGNORE INTO categories (id, category_name) VALUES (15, '小倉トースト');
INSERT IGNORE INTO categories (id, category_name) VALUES (16, '鬼まんじゅう');
INSERT IGNORE INTO categories (id, category_name) VALUES (17, '鉄板スパゲッティ');
INSERT IGNORE INTO categories (id, category_name) VALUES (18, '味噌カツ');
INSERT IGNORE INTO categories (id, category_name) VALUES (19, 'えびせんべい');
INSERT IGNORE INTO categories (id, category_name) VALUES (20, '味噌煮込みうどん');
INSERT IGNORE INTO categories (id, category_name) VALUES (21, 'ひつまぶし');
INSERT IGNORE INTO categories (id, category_name) VALUES (22, '台湾ラーメン');

-- shopsテーブル--
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (1,'SAMURAI', 'shop002.jpg', 2, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2000, 5, '073-0145', '愛知県名古屋市西五条南X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (2,'御飯SAMURAI', 'shop001.jpg', 1, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 500, 1000, 3, '030-0945', '愛知県名古屋市飯沼X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (3,'SAMURAI荘', 'shop004.jpg', 4, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 3000, 4, '029-5618', '愛知県名古屋市伏見区X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (4,'名古屋SAMURAI', 'shop003.jpg', 3, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2000, 5, '989-0555', '愛知県豊田市X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (5,'SAMURAI屋', 'shop006.jpg', 6, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2000, 6, '018-2661', '愛知県名古屋市東新町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (6,'食のSAMURAI', 'shop005.jpg', 5, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2000, 10, '999-6708', '愛知県名古屋市東新町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (7,'SAMURAIGOHAN', 'shop008.jpg',8, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 3500, 4000, 7, '969-5147', '愛知県名古屋市駒込X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (8,'やっぱりSAMURAI', 'shop007.jpg',7, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 1500, 6, '310-0021', '愛知県名古屋市泉郷町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (9,'いきなりSAMURAI', 'shop010.jpg',10, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 3000, 5, '323-1101', '愛知県名古屋市泉郷町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (10,'くらSAMURAI', 'shop009.jpg',9, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 3000, 6, '370-0806', '愛知県豊田市X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (11,'SAMURAIハマ', 'shop012.jpg',12, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2500, 8, '344-0125', '愛知県名古屋市飯沼X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (12,'マクドSAMURAI', 'shop011.jpg',11, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 3000, 3, '272-0011', '愛知県豊田市X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (13,'SAMURAIまいど', 'shop013.jpg',6, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 1500, 4, '130-0023', '愛知県名古屋市飯沼X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (14,'キャッツSAMURAI', 'shop015.jpg',15, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2000, 5, '240-0006', '愛知県名古屋市大明見X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (15,'SAMURAI館', 'shop014.jpg',14, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 1500, 6, '950-0201', '愛知県名古屋市駒込X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (16,'SAMURAI山荘', 'shop017.jpg',17, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 1200, 5, '939-8155', '愛知県名古屋市稲生町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (17,'SAMURAIの家', 'shop016.jpg',16, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 1500, 3, '929-0111', '愛知県名古屋市尾野X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (18,'侍の食', 'shop018.jpg',4, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2000, 4, '910-2354', '愛知県名古屋市東新町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (19,'これから侍', 'shop019.jpg',15, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 500, 1000, 5, '403-0003', '愛知県名古屋市大明見X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (20,'侍荘', 'shop021.jpg',21, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 500, 1000, 6, '395-0017', '愛知県名古屋市東新町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (21,'みんな侍', 'shop020.jpg',20, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 3000, 5000, 20, '509-5147', '愛知県名古屋市泉郷町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (22,'侍屋', 'shop020.jpg',20, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 3000, 15, '434-0002', '愛知県名古屋市尾野X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (23,'御飯処 侍', 'shop023.jpg',11, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2000, 8, '444-3261', '愛知県豊田市東大林町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (24,'侍だらけ', 'shop024.jpg',20, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 500, 1000, 5, '510-0201', '愛知県名古屋市稲生町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (25,'こんな侍', 'shop025.jpg',21, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 500, 1000, 6, '520-2353', '愛知県名古屋市尾野X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (26,'侍ショップ', 'shop026.jpg',22, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 3000, 15, '612-8369', '愛知県名古屋市伏見区村上町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (27,'よって侍', 'shop027.jpg',8, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2000, 3, '578-0915', '愛知県名古屋市飯沼X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (28,'侍ならば', 'shop028.jpg',7, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 3000, 5000, 4, '655-0891', '愛知県名古屋市垂水区山手X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (29,'よって侍', 'shop029.jpg',5, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 2000, 5, '630-1126', '愛知県名古屋市法用町X-XX-XX', '012-345-678');
INSERT IGNORE  INTO shops (id, name, image_name, category_id, description, lower_price, upper_price, capacity, post_code, address, phone_number) VALUES (30,'食べてや侍', 'shop030.jpg',16, '最寄り駅から徒歩10分。自然豊かで閑静な場所にあります。ご予約・テイクアウトも可能です。', 1000, 3000, 5, '630-1126', '愛知県名古屋市法用町X-XX-XX', '012-345-678');

-- reservationsテーブル--
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservation_date, number_of_people, amount) VALUES (1, 1, 1, '2023-04-01', 2, 4000);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservation_date, number_of_people, amount) VALUES (2, 2, 1, '2023-04-01', 3, 3000);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservation_date, number_of_people, amount) VALUES (3, 3, 1, '2023-04-01', 4, 12000);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservation_date, number_of_people, amount) VALUES (4, 4, 1, '2023-04-01', 5, 10000);
INSERT IGNORE INTO reservations (id, shop_id, user_id, reservation_date, number_of_people, amount) VALUES (5, 5, 1, '2023-04-01', 6, 12000);

-- reviewテーブル--
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (1, 1, 1, 5, '素晴らしい店でした。また利用したいです。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (2, 2, 1, 4, '駅から近く、プライベートな空間を確保できました。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (3, 3, 5, 3, '店内もきれいでとてもよかったです。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (4, 4, 1, 5, '従業員の方々は優しく、ゆったりできました。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (5, 5, 7, 4, 'お味も最高でした。また利用したいです。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (6, 6, 8, 3, '自分はとても気に入ったので、家族にも紹介しました。ぜひまた利用したいです。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (7, 7, 9, 4, '2度目の利用ですが、コスパ最強です。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (8, 8, 1, 4, 'リーズナブルな店を希望される方にはおすすめできます。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (9, 9, 1, 5, '値段も安く、機会があればリピートしたいと思います。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (10, 10, 2, 3, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (11, 11, 3, 4, '今回初めて利用させてもらいました。味もおいしくて大変満足でした。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (12, 30, 4, 3, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (13, 13, 1, 5, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (14, 14, 6, 4, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (15, 15, 7, 3, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (16, 16, 1, 5, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (17, 17, 9, 4, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (18, 18, 1, 3, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (19, 19, 12, 5, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (20, 30, 13, 4, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (21, 21, 1, 3, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (22, 22, 2, 5, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (23, 23, 3, 4, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (24, 24, 4, 3, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (25, 25, 5, 5, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (26, 26, 6, 4, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (27, 27, 7, 3, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (28, 28, 8, 5, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (29, 29, 9, 4, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (30, 30, 10, 3, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (31, 3, 1, 5, '素晴らしい店でした。また利用したいです。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (32, 30, 2, 4, '駅から近く、プライベートな空間を確保できました。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (33, 4, 3, 3, '店内もきれいでとてもよかったです。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (34, 5, 4, 5, '従業員の方々は優しく、ゆったりできました。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (35, 6, 5, 4, 'お味も最高でした。また利用したいです。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (36, 7, 6, 3, '自分はとても気に入ったので、家族にも紹介しました。ぜひまた利用したいです。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (37, 8, 7, 4, '2度目の利用ですが、コスパ最強です。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (38, 9, 2, 4, 'リーズナブルな店を希望される方にはおすすめできます。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (39, 11, 8, 5, '値段も安く、機会があればリピートしたいと思います。');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (40, 13, 9, 3, 'スタッフの方とても親切でした！');
INSERT IGNORE INTO review (id, shop_id, user_id, score, review_text) VALUES (41, 30, 1, 4, '今回初めて利用させてもらいました。味もおいしくて大変満足でした。');

-- favoriteテーブル --
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (1, 3, 1);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (2, 4, 2);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (3, 5, 3);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (4, 6, 4);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (5, 7, 5);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (6, 8, 6);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (7, 9, 7);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (8, 10, 8);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (9, 3, 9);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (10, 4, 10);
INSERT IGNORE INTO favorite (id, user_id, shop_id) VALUES (11, 5, 11);

-- salesテーブル --
INSERT IGNORE INTO sales (user_id, start_date, end_date, amount) VALUES (4, '2021-06-01', '2023-08-01', 300);
INSERT IGNORE INTO sales (user_id, start_date, end_date, amount) VALUES (6, '2022-05-01', '2023-12-01', 300);
INSERT IGNORE INTO sales (user_id, start_date, end_date, amount) VALUES (10, '2021-08-01', '2023-09-01', 300);
INSERT IGNORE INTO sales (user_id, start_date, end_date, amount) VALUES (11, '2024-01-01', '2024-06-01', 300);
INSERT IGNORE INTO sales (user_id, start_date, end_date, amount) VALUES (12, '2023-09-01', '2024-05-01', 300);
INSERT IGNORE INTO sales (user_id, start_date, end_date, amount) VALUES (13, '2021-06-01', '2024-04-01', 300);