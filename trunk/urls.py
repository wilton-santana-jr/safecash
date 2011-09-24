from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('',
    # HOME
    (r'^home/$', 'apps.home.views.home'),
    
    # LIVRO
    (r'^livro/$', 'apps.livro.views.home'),
    
    # CONTA
    (r'^contas/$', 'apps.conta.views.home'),
    
    # PROJETO
    (r'^projetos/$', 'apps.projeto.views.home'),
    
    # REQUISICAO
    (r'^requisicao/$', 'apps.requisicao.views.home'),
    
    # RELATORIO
    (r'^relatorios/$', 'apps.relatorio.views.home'),
    
    # MODELO
    (r'^modelos/$', 'apps.modelo.views.home'),
)
