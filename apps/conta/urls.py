from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# CONTA
urlpatterns = patterns('apps.conta.views',
    (r'^$', 'home'),
    (r'^insert/$', 'insert'),
    (r'^editarConta/$', 'editarConta'),
    (r'^alterarConta/$', 'alterarConta'),
(r'^lista_dinamica/$', 'lista_dinamica'))
