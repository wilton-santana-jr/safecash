from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# MODELO
urlpatterns = patterns('apps.modelo.views',
    (r'^$', 'home'))
