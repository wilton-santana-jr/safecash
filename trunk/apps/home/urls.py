from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# HOME
urlpatterns = patterns('apps.home.views',
    (r'^login/$', 'login'),
    (r'^home/$', 'home'),
    (r'^home/remove_usr/$', 'remove_usr'),
    (r'^home/edit_usr/$', 'edit_usr'),
    (r'^home/add_usr/$', 'add_usr'))
