SUMMARY = "Basic Odroid X11 graphics image"

IMAGE_FEATURES += "splash debug-tweaks ssh-server-openssh tools-debug x11-base"

LICENSE = "MIT"

inherit core-image features_check
# populate_sdk_qt5

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "x11"


QT5 = "\
    packagegroup-qt5 \
    packagegroup-qt5-apps \
"

IMAGE_INSTALL = " \
    ${CORE_IMAGE_BASE_INSTALL} \
    ${XSERVER} \
    ${QT5} \
    kernel-modules \
    odroid-edid \
    udev-extraconf \
    libxinerama \
    x11perf \
"

COMPATIBLE_MACHINE = "(odroid-xu3|odroid-xu4|odroid-xu3-lite|odroid-c2)"
