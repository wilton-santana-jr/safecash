from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# REQUISICAO
urlpatterns = patterns('apps.requisicao.views',
    (r'^$', 'home'),
	(r'^visualizar/', 'visualizar'),
	(r'^salvarEstado/', 'salvarEstado'))
