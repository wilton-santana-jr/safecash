from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

# HOME
urlpatterns = patterns('apps.home.views',
    (r'^$', 'index'),
    (r'^logout/$', 'logout_usr'),
    (r'^home/$', 'login_usr'),
    (r'^home/remove_usr/$', 'remove_usr'),
    (r'^home/edit_usr/$', 'edit_usr'),
    (r'^home/add_usr/$', 'add_usr'))
