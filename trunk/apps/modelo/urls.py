from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# MODELO
urlpatterns = patterns('apps.modelo.views',
    (r'^$', 'home'),
    (r'^get_more_info/', 'get_more_info'),
    (r'^remove/', 'remove'),
    (r'^download/', 'download'),
    (r'^insert/', 'insert')
)
