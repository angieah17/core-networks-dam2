FROM php:8.2-apache

# Instalar extensiones MySQL necesarias
RUN docker-php-ext-install mysqli pdo pdo_mysql

# Habilitar mod_rewrite (opcional, útil para URLs limpias)
RUN a2enmod rewrite