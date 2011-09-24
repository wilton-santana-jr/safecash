from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# HOME
urlpatterns = patterns('apps.home.views',
    (r'^home/$', 'home'),
    (r'^login/$', 'login'))
    
# LIVRO
urlpatterns += patterns('apps.livro.views',
    (r'^livro/$', 'home'))
    
# CONTA
urlpatterns += patterns('apps.conta.views',
    (r'^contas/$', 'home'))
    
# PROJETO
urlpatterns += patterns('apps.projeto.views',
    (r'^projetos/$', 'home'))
    
# REQUISICAO
urlpatterns += patterns('apps.requisicao.views',
    (r'^requisicao/$', 'home'))
    
# RELATORIO
urlpatterns += patterns('apps.relatorio.views',
    (r'^relatorios/$', 'home'))
    
# MODELO
urlpatterns += patterns('apps.modelo.views',
    (r'^modelos/$', 'home'))
