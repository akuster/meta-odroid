SUMMARY = "Basic Odroid X11 graphics image"

IMAGE_FEATURES += "splash debug-tweaks ssh-server-openssh tools-debug x11-base"

LICENSE = "MIT"

inherit core-image features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "x11"

VIRTUAL-RUNTIME_mesa ?= ""
VIRTUAL-RUNTIME_graphical_init_manager ?= ""

MESA_PKGS ?= "libgl-mesa mesa-megadriver libglapi"

IMAGE_INSTALL = " \
    ${CORE_IMAGE_BASE_INSTALL} \
    ${XSERVER} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '${MESA_PKGS}', '', d)} \
    kernel-modules \
    udev-extraconf \
"

CORE_IMAGE_BASE_INSTALL += " \
    x11perf \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'mesa-demos', '', d)} \
"

COMPATIBLE_MACHINE = "(odroid-xu3|odroid-xu4|odroid-xu3-lite|odroid-c2|odroid-n2)"
