INSERT INTO mst_category (id, category_name, category_description, created_at, updated_at) VALUES (1, '食事パン', '食事用のパン', current_timestamp, current_timestamp);
INSERT INTO mst_category (id, category_name, category_description, created_at, updated_at) VALUES (2, 'おかずパン', '小腹が減った時用', current_timestamp, current_timestamp);
INSERT INTO mst_category (id, category_name, category_description, created_at, updated_at) VALUES (3, '菓子パン', 'おやつに最適', current_timestamp, current_timestamp);
INSERT INTO mst_category (id, category_name, category_description, created_at, updated_at) VALUES (4, '糖質OFF', '痩せたいあなたへ', current_timestamp, current_timestamp);

INSERT INTO mst_product (id, product_name, product_name_kana, product_description, category_id, price, image_full_path, release_date, release_company,  created_at, updated_at) VALUES (1, '食パン', 'しょくぱん', 'ふわふわな食感です', 1, 500, '', '2019/01/01', 'サンプル株式会社', current_timestamp, current_timestamp);
INSERT INTO mst_product (id, product_name, product_name_kana, product_description, category_id, price, image_full_path, release_date, release_company,  created_at, updated_at) VALUES (2, 'クロワッサン', 'くろわっさん', 'サクサクな食感です', 1, 340, '', '2019/01/01', 'サンプル株式会社', current_timestamp, current_timestamp);
INSERT INTO mst_product (id, product_name, product_name_kana, product_description, category_id, price, image_full_path, release_date, release_company,  created_at, updated_at) VALUES (3, 'ウインナーロール', 'ういんなーろーる', 'カリカリな食感です', 2, 280, '', '2019/01/01', 'サンプル株式会社', current_timestamp, current_timestamp);
INSERT INTO mst_product (id, product_name, product_name_kana, product_description, category_id, price, image_full_path, release_date, release_company,  created_at, updated_at) VALUES (4, '焼きそばパン', 'やきそばぱん', '香ばしい香りです', 2, 280, '', '2019/01/01', 'サンプル株式会社', current_timestamp, current_timestamp);
INSERT INTO mst_product (id, product_name, product_name_kana, product_description, category_id, price, image_full_path, release_date, release_company,  created_at, updated_at) VALUES (5, 'メロンパン', 'めろんぱん', 'さくふわな食感です', 3, 250, '', '2019/01/01', 'サンプル株式会社', current_timestamp, current_timestamp);
INSERT INTO mst_product (id, product_name, product_name_kana, product_description, category_id, price, image_full_path, release_date, release_company,  created_at, updated_at) VALUES (6, 'あんパン', 'あんぱん', '中にあんこが入ってます', 3, 250, '', '2019/01/01', 'サンプル株式会社', current_timestamp, current_timestamp);
INSERT INTO mst_product (id, product_name, product_name_kana, product_description, category_id, price, image_full_path, release_date, release_company,  created_at, updated_at) VALUES (7, 'ベーグル', 'ベーぐる', 'もっちりな食感です', 4, 180, '', '2019/01/01', 'サンプル株式会社', current_timestamp, current_timestamp);
INSERT INTO mst_product (id, product_name, product_name_kana, product_description, category_id, price, image_full_path, release_date, release_company,  created_at, updated_at) VALUES (8, 'ロールパン', 'ろーるぱん', '老若男女に人気です', 4, 180, '', '2019/01/01', 'サンプル株式会社', current_timestamp, current_timestamp);

select * from mst_category;
select * from mst_product;

update mst_product set image_full_path = '/img/WhiteBread.jpg' where id = 1;
update mst_product set image_full_path = '/img/CrescentRoll.jpg' where id = 2;
update mst_product set image_full_path = '/img/WienerRoll.jpg' where id = 3;
update mst_product set image_full_path = '/img/YakisobaPan.jpg' where id = 4;
update mst_product set image_full_path = '/img/MelonPan.jpg' where id = 5;
update mst_product set image_full_path = '/img/Anpan.jpg' where id = 6;
update mst_product set image_full_path = '/img/Bagle.jpg' where id = 7;
update mst_product set image_full_path = '/img/Roll.jpg' where id = 8
