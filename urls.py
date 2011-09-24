from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = patterns('',
    # HOME
    (r'^home/$', 'home.views.home'),
    
    # LIVRO
    (r'^livro/$', 'livro.views.home'),
    
    # CONTA
    (r'^contas/$', 'conta.views.home'),
    
    # PROJETO
    (r'^projetos/$', 'projeto.views.home'),
    
    # REQUISICAO
    (r'^requisicao/$', 'requisicao.views.home'),
    
    # RELATORIO
    (r'^relatorios/$', 'relatorio.views.home'),
    
    # MODELO
    (r'^modelos/$', 'modelo.views.home'),
)
