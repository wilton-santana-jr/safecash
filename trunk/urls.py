from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# SOMENTO INCLUDES DAS APPS - NÃO ALTERAR
urlpatterns = patterns('',
    (r'^', include('apps.home.urls')),
    (r'^livro/', include('apps.livro.urls')),
    (r'^contas/', include('apps.conta.urls')),
    (r'^projetos/', include('apps.projeto.urls')),
    (r'^requisicao/', include('apps.requisicao.urls')),
    (r'^relatorios/', include('apps.relatorio.urls')),
    (r'^modelos/', include('apps.modelo.urls')),)
