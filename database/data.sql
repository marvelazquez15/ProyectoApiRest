-- 1. LIMPIEZA TOTAL
TRUNCATE TABLE TO004_OrderDetail, TO003_Order, TO002_Product, TO001_User RESTART IDENTITY CASCADE;

-- 2. USUARIOS
INSERT INTO TO001_User (fv_nombre, fv_apellido_paterno, fv_apellido_materno, fv_email, fn_telefono) VALUES
('Donovan', 'Fuentes', 'Lopez', 'donovan.fuentes@gmail.com', 5512345678),
('Javier', 'Ramos', 'Torres', 'javier.ramos@gmail.com', 5598745632),
('Alexia', 'Liñan', 'Tescha', 'alexia.linan@gmail.com', 5514785236),
('Antonio', 'Hernandez', 'Tescha', 'antonio.hdez@gmail.com', 5525896314),
('Jair', 'Sanchez', 'Tescha', 'jair.sanchez@gmail.com', 5596325874),
('Azucena', 'Garcia', 'Gomez', 'azucena.garcia@gmail.com', 5578412369),
('Roberto', 'Esquivel', 'Soto', 'roberto.esquivel@gmail.com', 5563214785),
('Lucia', 'Mendez', 'Reyes', 'lucia.mendez@gmail.com', 5541258963),
('Ricardo', 'Luna', 'Perez', 'ricardo.luna@gmail.com', 5532145698),
('Sofia', 'Castillo', 'Diaz', 'sofia.castillo@gmail.com', 5585236941);

-- 3. PRODUCTOS
INSERT INTO TO002_Product (fv_nombre, fv_descripcion, fv_marca, fn_precio, fi_stock) VALUES
('Teclado Inalámbrico', 'Conexión 2.4GHz y Bluetooth', 'TechPro', 850.00, 40),
('Mouse Ergonómico', 'Diseño vertical anti-fatiga', 'EasyClick', 450.00, 60),
('Disco Duro', 'SSD Externo 1TB USB 3.0', 'DataSafe', 1500.00, 25),
('Webcams', 'Resolución 1080p con micrófono', 'VisionX', 1100.00, 30),
('Tarjeta Gráfica', 'GPU 8GB GDDR6 para gaming', 'PowerGraphics', 7500.00, 10),
('Monitor 24"', 'Panel IPS Full HD 75Hz', 'ViewMaster', 3200.00, 15),
('Audífonos Gamer', 'Sonido Surround 7.1 RGB', 'SoundBlast', 1250.00, 45),
('Memoria RAM 16GB', 'DDR4 3200MHz CL16', 'SpeedMax', 950.00, 50),
('Gabinete ATX', 'Cristal templado y 3 fans', 'CoolCase', 1800.00, 12),
('Fuente de Poder', '650W 80 Plus Gold Modular', 'VoltCore', 2100.00, 20);

-- 4. ÓRDENES
INSERT INTO TO003_Order (fi_user_id, fn_subtotal, fn_iva, fn_total, fv_metodopago, fi_id_usuario_creacion) VALUES 
(1, 850.00, 136.00, 986.00, 'Efectivo', 1), 
(2, 450.00, 72.00, 522.00, 'Tarjeta', 1),
(3, 1500.00, 240.00, 1740.00, 'Transferencia', 1),
(4, 1100.00, 176.00, 1276.00, 'Tarjeta', 1),
(5, 7500.00, 1200.00, 8700.00, 'Efectivo', 1),
(6, 3200.00, 512.00, 3712.00, 'Transferencia', 1),
(7, 1250.00, 200.00, 1450.00, 'Tarjeta', 1),
(8, 950.00, 152.00, 1102.00, 'Efectivo', 1),
(9, 1800.00, 288.00, 2088.00, 'Tarjeta', 1),
(10, 2100.00, 336.00, 2436.00, 'Transferencia', 1);

-- 5. DETALLE DE ÓRDENES
INSERT INTO TO004_OrderDetail (fi_order_id, fi_product_id, fi_cantidad) VALUES
(1, 1, 1), (2, 2, 1), (3, 3, 1), (4, 4, 1), (5, 5, 1),
(6, 6, 1), (7, 7, 1), (8, 8, 1), (9, 9, 1), (10, 10, 1);