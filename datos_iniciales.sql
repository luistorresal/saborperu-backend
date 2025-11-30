-- =====================================================
-- SCRIPT PARA INSERTAR DATOS INICIALES EN SABORPERU
-- Ejecutar en MySQL Workbench
-- =====================================================

-- Usar la base de datos
USE saborperu_db;

-- =====================================================
-- 1. INSERTAR CATEGORÍAS
-- =====================================================
INSERT INTO categorias (nombre, descripcion) VALUES 
('Snacks y Dulces', 'Deliciosos snacks y dulces tradicionales peruanos'),
('Postres Tradicionales', 'Postres típicos de la gastronomía peruana'),
('Insumos y Superalimentos', 'Ingredientes y superalimentos andinos');

-- =====================================================
-- 2. INSERTAR PRODUCTOS
-- =====================================================

-- Snacks y Dulces (categoria_id = 1)
INSERT INTO productos (nombre, descripcion, precio, imagen, stock, categoria_id) VALUES 
('Alfajores', 'Caja 6 unidades', 1990.00, 'https://images.unsplash.com/photo-1558326567-98ae2405596b?w=400', 50, 1),
('Chifles', 'Bolsa 150 g', 1590.00, 'https://images.unsplash.com/photo-1566478989037-eec170784d0b?w=400', 100, 1),
('King Kong (porción)', '120 g - Dulce de manjarblanco', 4490.00, 'https://images.unsplash.com/photo-1551024601-bec78aea704b?w=400', 30, 1),
('Turrón de Doña Pepa', '500 g - Tradicional de Lima', 2290.00, 'https://images.unsplash.com/photo-1509440159596-0249088772ff?w=400', 40, 1);

-- Postres Tradicionales (categoria_id = 2)
INSERT INTO productos (nombre, descripcion, precio, imagen, stock, categoria_id) VALUES 
('Mazamorra Morada', 'Porción 400 g - Con frutas', 3490.00, 'https://images.unsplash.com/photo-1488477181946-6428a0291777?w=400', 25, 2),
('Suspiro Limeño', 'Vaso 250 ml - Receta tradicional', 3990.00, 'https://images.unsplash.com/photo-1587314168485-3236d6710814?w=400', 20, 2),
('Crema Volteada', 'Porción 150 g - Con caramelo', 3790.00, 'https://images.unsplash.com/photo-1470124182917-cc6e71b22ecc?w=400', 25, 2);

-- Insumos y Superalimentos (categoria_id = 3)
INSERT INTO productos (nombre, descripcion, precio, imagen, stock, categoria_id) VALUES 
('Ají Amarillo (pasta)', 'Frasco 400 g - Para cocina peruana', 2990.00, 'https://images.unsplash.com/photo-1583119022894-919a68a3d0e3?w=400', 60, 3),
('Ají Panca (pasta)', 'Frasco 400 g - Suave y aromático', 2990.00, 'https://images.unsplash.com/photo-1526346698789-22fd84314424?w=400', 60, 3),
('Ají Limo (pasta)', 'Frasco 400 g - Picante tradicional', 3290.00, 'https://images.unsplash.com/photo-1590005354167-6da97870c757?w=400', 45, 3),
('Quinua Andina', 'Bolsa 1 kg - Superalimento peruano', 5990.00, 'https://images.unsplash.com/photo-1586201375761-83865001e31c?w=400', 80, 3);

-- =====================================================
-- VERIFICAR DATOS INSERTADOS
-- =====================================================
SELECT 'Categorías insertadas:' as mensaje;
SELECT * FROM categorias;

SELECT 'Productos insertados:' as mensaje;
SELECT p.id, p.nombre, p.precio, c.nombre as categoria 
FROM productos p 
LEFT JOIN categorias c ON p.categoria_id = c.id;

