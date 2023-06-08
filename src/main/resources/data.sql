/*Insertar datos de prueba
  Insertando datos de prueba en la base de datos de restaurante, categorias,
  platos, ordenes
 */
 -- Insertando datos de prueba en la tabla de restaurante
INSERT INTO `restaurant` (`id`, `address`, `name`, `nit`, `phone_number`, `url_logo`, `owner_id`) VALUES (1, 'Calle 27', 'Restaurante1', '1', '31054789547', 'https://assets-global.website-files.com/6257adef93867e50d84d30e2/636e0a6918e57475a843f59f_icon_clyde_black_RGB.svg', 2);
INSERT INTO `restaurant` (`id`, `address`, `name`, `nit`, `phone_number`, `url_logo`, `owner_id`) VALUES (2, 'Calle 28', 'Restaurante2', '2', '31054789548', 'https://assets-global.website-files.com/6257adef93867e50d84d30e2/636e0a6918e57475a843f59f_icon_clyde_black_RGB.svg', 5);
INSERT INTO `restaurant` (`id`, `address`, `name`, `nit`, `phone_number`, `url_logo`, `owner_id`) VALUES (3, 'Calle 29', 'a', '3', '31054789549', 'https://assets-global.website-files.com/6257adef93867e50d84d30e2/636e0a6918e57475a843f59f_icon_clyde_black_RGB.svg', 2);
INSERT INTO `restaurant` (`id`, `address`, `name`, `nit`, `phone_number`, `url_logo`, `owner_id`) VALUES (4, 'Calle 30', 'b', '4', '31054789540', 'https://assets-global.website-files.com/6257adef93867e50d84d30e2/636e0a6918e57475a843f59f_icon_clyde_black_RGB.svg', 5);
-- Insertando datos de prueba en la tabla de categorias
INSERT INTO `category` (`id`, `description`, `name`) VALUES (1, 'Comida rapida', 'Comida rapida');
INSERT INTO `category` (`id`, `description`, `name`) VALUES (2, 'Comida china', 'Comida china');
-- Insertando datos de prueba en la tabla de platos
-- Categoria 1
INSERT INTO `dishes` (`id`, `is_active`, `description`, `image_url`, `name`, `price`, `id_category`, `id_restaurant`) VALUES (1, 1, 'Hamburguesa de queso', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_202006_0003_Cheeseburger_StraightBun_832x472', 'Hamburguesa de queso', 10000, 1, 1);
INSERT INTO `dishes` (`id`, `is_active`, `description`, `image_url`, `name`, `price`, `id_category`, `id_restaurant`) VALUES (2, 1, 'Hamburguesa de pollo', 'https://s7d1.scene7.com/is/image/mcdonalds/DC_201909_4314_McChicken_832x472', 'Hamburguesa de pollo', 10000, 1, 1);
-- Categoria 2
INSERT INTO `dishes` (`id`, `is_active`, `description`, `image_url`, `name`, `price`, `id_category`, `id_restaurant`) VALUES (3, 1, 'Arroz chino', 'https://www.comedera.com/wp-content/uploads/2014/01/arroz-chino-casero.jpg', 'Arroz chino', 10000, 2, 1);
INSERT INTO `dishes` (`id`, `is_active`, `description`, `image_url`, `name`, `price`, `id_category`, `id_restaurant`) VALUES (4, 1, 'Tallarines', 'https://www.comedera.com/wp-content/uploads/2020/03/chopsuey1-1536x1152.jpeg', 'Chop suey', 10000, 2, 1);
-- Insertando datos de prueba en la tabla de ordenes
INSERT INTO `orders` (`id`, `order_date`, `status`, `id_chef`, `id_restaurant`, `id_client`) VALUES (1, '2020-10-10', 'En proceso', 3, 1, 4);
INSERT INTO `orders` (`id`, `order_date`, `status`, `id_chef`, `id_restaurant`, `id_client`) VALUES (2, '2020-10-10', 'En proceso', 3, 1, 4);
INSERT INTO `orders` (`id`, `order_date`, `status`, `id_chef`, `id_restaurant`, `id_client`) VALUES (3, '2020-10-10', 'En proceso', 3, 1, 4);
-- Insertando datos de prueba en la tabla de ordenes platos
INSERT INTO `dishes_ordered` (`id`, `dishes`, `orders`, `quantity`) VALUES (1, 1, 1, 2);
INSERT INTO `dishes_ordered` (`id`, `dishes`, `orders`, `quantity`) VALUES (2, 2, 1, 1);
INSERT INTO `dishes_ordered` (`id`, `dishes`, `orders`, `quantity`) VALUES (3, 3, 2, 1);