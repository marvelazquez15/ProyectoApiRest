--borrar tablas en orden (de hijos a padres) para evitar errores de integridad
DROP TABLE IF EXISTS to004_order_detail;
DROP TABLE IF EXISTS to003_order;
DROP TABLE IF EXISTS to002_product;
DROP TABLE IF EXISTS to001_user;

--gestión de usuarios
create table TO001_User (
    fs_id SERIAL primary key ,
    fv_nombre VARCHAR(50) not null,
    fv_apellido_paterno VARCHAR(50) not null,
    fv_apellido_materno VARCHAR(50),
    fv_email VARCHAR(100) unique not null,
    fn_telefono NUMERIC(10) unique not null
);

--gestión de productos 
create table TO002_Product (
    fs_id SERIAL primary key,
    fv_nombre VARCHAR(50) not null,
    fv_descripcion VARCHAR(100),
    fv_marca VARCHAR(50) not null,
    fn_precio NUMERIC(10, 2) not null check (fn_precio  >= 0),
    fi_stock INTEGER not null check (fi_stock >= 0)
);

--greación de órdenes 
create table TO003_Order (
    fs_id SERIAL primary key,
    ft_fecha TIMESTAMP default CURRENT_TIMESTAMP not null,
    fi_user_id INTEGER not null, -- Debe ser INTEGER para referenciar al ID de User
    fn_subtotal NUMERIC(10, 2) not null check (fn_subtotal  >= 0),
    fn_iva NUMERIC(10, 2) not null check (fn_iva  >=0),
    fn_total NUMERIC(10, 2) not null check (fn_total >=0),
    fv_metodopago VARCHAR(20) not null,
    fi_id_usuario_creacion INTEGER,
    ft_fecha_creacion TIMESTAMP default CURRENT_TIMESTAMP,
    fi_id_usuario_modificacion INTEGER,
    ft_fecha_modificacion TIMESTAMP,
    constraint fk_user foreign key (fi_user_id) references TO001_User(fs_id)
);

--detalle de órdenes
create table TO004_OrderDetail (
    fs_id SERIAL primary key,
    fi_order_id INTEGER not null,
    fi_product_id INTEGER not null,
    fi_cantidad INTEGER not null check (fi_cantidad > 0),
    constraint fk_order foreign key (fi_order_id) references TO003_Order(fs_id) on delete cascade,
    constraint fk_product foreign key (fi_product_id) references TO002_Product(fs_id)
);