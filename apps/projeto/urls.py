from django.conf.urls.defaults import *

# PROJETO
urlpatterns = patterns('apps.projeto.views',
    (r'^$', 'home'),
    (r'^(?P<ano_filtro>[\d]+)', 'home'),
    (r'^cadatro_projeto_ajax', 'cadatro_projeto_ajax'),
    (r'^detalhes', 'detalhes'),
    (r'^editar', 'editar'),
    (r'^remover/(?P<id_projeto>[\d]+)', 'remover')
)
