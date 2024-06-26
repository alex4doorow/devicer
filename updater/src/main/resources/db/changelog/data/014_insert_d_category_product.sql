--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100016-1
INSERT INTO D_CATEGORY_PRODUCT (ID, TYPE_GROUP, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (0, 'прочие', 'не определена', 'A', 1, localtimestamp, localtimestamp)
,(101, 'отпугиватели', 'отпугиватели кротов', 'A', 1, localtimestamp, localtimestamp)
,(102, 'отпугиватели', 'отпугиватели змей', 'A', 1, localtimestamp, localtimestamp)
,(103, 'отпугиватели', 'отпугиватели птиц', 'A', 1, localtimestamp, localtimestamp)
,(104, 'отпугиватели', 'отпугиватели грызунов', 'A', 1, localtimestamp, localtimestamp)
,(105, 'отпугиватели', 'отпугиватели комаров', 'A', 1, localtimestamp, localtimestamp)
,(107, 'отпугиватели', 'отпугиватели ос', 'A', 1, localtimestamp, localtimestamp)
,(108, 'отпугиватели', 'отпугиватели собак', 'A', 1, localtimestamp, localtimestamp)
,(109, 'отпугиватели', 'уничтожители насекомых', 'A', 1, localtimestamp, localtimestamp)
,(110, 'отпугиватели', 'отпугиватели клещей', 'A', 1, localtimestamp, localtimestamp)
,(201, 'для дома', 'gsm розетки и реле', 'A', 1, localtimestamp, localtimestamp)
,(202, 'для дома', 'gsm сигнализации', 'A', 1, localtimestamp, localtimestamp)
,(203, 'для дома', 'автономные извещатели', 'A', 1, localtimestamp, localtimestamp)
,(204, 'для дома', 'видеоглазки и видеодомофоны', 'A', 1, localtimestamp, localtimestamp)
,(205, 'для дома', 'видеонаблюдение', 'A', 1, localtimestamp, localtimestamp)
,(206, 'для дома', 'ножеточки', 'A', 1, localtimestamp, localtimestamp)
,(207, 'для дома', 'эконаборы', 'A', 1, localtimestamp, localtimestamp)
,(208, 'для дома', 'светильники', 'A', 1, localtimestamp, localtimestamp)
,(209, 'для дома', 'столики для ноутбука', 'A', 1, localtimestamp, localtimestamp)
,(210, 'для дома', 'роботы для уборки', 'A', 1, localtimestamp, localtimestamp)
,(211, 'для дома', 'средства защиты', 'A', 1, localtimestamp, localtimestamp)
,(301, 'для автомобиля', 'алкотестеры', 'A', 1, localtimestamp, localtimestamp)
,(302, 'для автомобиля', 'пуско-зарядные устройства', 'A', 1, localtimestamp, localtimestamp)
,(303, 'для автомобиля', 'гибкие камеры', 'A', 1, localtimestamp, localtimestamp)
,(304, 'для автомобиля', 'гаджеты', 'A', 1, localtimestamp, localtimestamp)
,(401, 'для дачи', 'изотермика', 'A', 1, localtimestamp, localtimestamp)
,(403, 'для дачи', 'мобильный душ', 'A', 1, localtimestamp, localtimestamp)
,(404, 'для дачи', 'системы полива', 'A', 1, localtimestamp, localtimestamp)
,(405, 'для дачи', 'термосы', 'A', 1, localtimestamp, localtimestamp)
,(501, 'для детей', 'микроскопы USB', 'A', 1, localtimestamp, localtimestamp)
,(502, 'для детей', 'домашние планетарии', 'A', 1, localtimestamp, localtimestamp)
,(503, 'для детей', 'видеоняни', 'A', 1, localtimestamp, localtimestamp)
,(504, 'для детей', 'конструкторы', 'A', 1, localtimestamp, localtimestamp)
,(601, 'безопасность', 'антижучки', 'A', 1, localtimestamp, localtimestamp)
,(602, 'безопасность', 'обнаружители видеокамер', 'A', 1, localtimestamp, localtimestamp)
,(603, 'безопасность', 'подавители диктофонов', 'A', 1, localtimestamp, localtimestamp)
,(604, 'безопасность', 'подавители сотовых телефонов', 'A', 1, localtimestamp, localtimestamp)
,(701, 'путешествия', 'стельки с подогревом', 'A', 1, localtimestamp, localtimestamp)
,(702, 'путешествия', 'возвращатели', 'A', 1, localtimestamp, localtimestamp)
,(703, 'путешествия', 'мини электростанции', 'A', 1, localtimestamp, localtimestamp)
,(801, 'музыка', 'наушники', 'A', 1, localtimestamp, localtimestamp)
,(802, 'музыка', 'колонки', 'A', 1, localtimestamp, localtimestamp)
,(901, 'для домашних животных', 'фурминатор для кошек', 'A', 1, localtimestamp, localtimestamp)
,(902, 'для домашних животных', 'фурминатор для собак', 'A', 1, localtimestamp, localtimestamp)
,(903, 'для домашних животных', 'автокормушки и автопоилки', 'A', 1, localtimestamp, localtimestamp)
,(904, 'для домашних животных', 'электронные ошейники', 'A', 1, localtimestamp, localtimestamp)
,(1111, 'производство', 'инкубаторы', 'A', 1, localtimestamp, localtimestamp)
,(1101, 'прочие', 'элементы питания', 'A', 1, localtimestamp, localtimestamp)
,(1102, 'прочие', 'инструменты', 'A', 1, localtimestamp, localtimestamp)
,(1103, 'прочие', 'подарки', 'A', 1, localtimestamp, localtimestamp)
,(1104, 'прочие', 'для рыбалки', 'A', 1, localtimestamp, localtimestamp)
,(1105, 'прочие', 'для охоты', 'A', 1, localtimestamp, localtimestamp)
,(1106, 'прочие', 'фонари', 'A', 1, localtimestamp, localtimestamp);