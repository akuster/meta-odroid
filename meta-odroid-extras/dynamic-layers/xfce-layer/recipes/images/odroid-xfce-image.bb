SUMMARY = "Basic Odroid xfce graphics image"

IMAGE_FEATURES += "splash debug-tweaks ssh-server-openssh tools-debug x11-base"

LICENSE = "MIT"

inherit core-image features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "x11"

VIRTUAL-RUNTIME_mesa ?= ""
VIRTUAL-RUNTIME_graphical_init_manager = ""

IMAGE_INSTALL = " \
    ${CORE_IMAGE_BASE_INSTALL} \
    ${XSERVER} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '${VIRTUAL-RUNTIME_mesa} ', '', d)} \
    packagegroup-xfce-base \
    kernel-modules \
    odroid-edid \
    udev-extraconf \
"

CORE_IMAGE_BASE_INSTALL += " \
    x11perf \
    xvideo-tests \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'mesa-demos', '', d)} \
"

COMPATIBLE_MACHINE = "(odroid-xu3|odroid-xu4|odroid-xu3-lite|odroid-c2)"
