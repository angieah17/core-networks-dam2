FROM php:8.2-apache

# Instalamos extensiones necesarias
RUN docker-php-ext-install pdo pdo_mysql

# Activamos mod_rewrite (por si luego lo usamos)
RUN a2enmod rewrite
