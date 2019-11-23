SUMMARY = "Basic Odroid X11 graphics image"

IMAGE_FEATURES += "splash debug-tweaks ssh-server-openssh tools-debug x11"

LICENSE = "MIT"

inherit core-image features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "vulkan"

MALI ?= ""

VULKAN = "\
    vulkan \
    vulkan-demos \
"

XSERVER = " \
    xserver-xorg \
    xserver-xorg-module-libint10 \
    xf86-video-fbdev', d)\
    xf86-video-armsoc \
    xf86-input-evdev \
    xf86-input-mouse \
    xf86-input-keyboard \
"

CORE_IMAGE_BASE_INSTALL += " \
    ${XSERVER} \
    ${MALI} \
    ${VALKAN} \
    xserver-common \
    xorg-minimal-fonts \
    xserver-xorg-utils \
    kernel-modules \
    openbox \
    mesa-gl \
"

CORE_IMAGE_BASE_INSTALL += " \
    x11perf \
    xvideo-tests \
"

COMPATIBLE_MACHINE = "(odroid-xu3|odroid-xu4|odroid-xu3-lite|odroid-c2)"
