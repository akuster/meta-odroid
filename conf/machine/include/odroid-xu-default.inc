# Odroid XU default BSP

include conf/machine/include/samsung-exynos.inc
include conf/machine/include/odroid-default-settings.inc

DEFAULTTUNE = "cortexa15thf-neon-vfpv4"
include conf/machine/include/tune-cortexa15.inc

UBOOT_FDT_LOADADDR = "0x40800000"
UBOOT_LOADADDR = "0x40007FC0"
UBOOT_LOADADDRESS = "0x40007FC0"
UBOOT_CONSOLE = "console=ttySAC2,115200"
UBOOT_KERNEL_NAME = "zImage"

WKS_FILE ?= "odroid_xu-u-boot.wks"

EXTRA_IMAGEDEPENDS += "secure-odroid"
MACHINE_FEATURES_append = " alsa screen"

VIRTUAL-RUNTIME_mesa = "mesa"

PREFERRED_PROVIDER_virtual/egl = "mali-t62x"
PREFERRED_PROVIDER_virtual/libgbm = "mali-t62x"
PREFERRED_PROVIDER_virtual/libgles1 = "mali-t62x"
PREFERRED_PROVIDER_virtual/libgles2 = "mali-t62x"
PREFERRED_PROVIDER_virtual/libopencl = "mali-t62x"
PREFERRED_PROVIDER_virtual/libgl = "${VIRTUAL-RUNTIME_mesa}"
PREFERRED_PROVIDER_virtual/mesa = "${VIRTUAL-RUNTIME_mesa}"

MALI = "\
    kernel-module-mali-t62x \
    mali-t62x\
"

XSERVER ?= " \
    xserver-xorg \
    xf86-video-fbdev \
    xf86-video-modesetting \
"

XSERVER_append = "  \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'xserver-xorg-extension-glx', '', d)} \
    ${@bb.utils.contains('MACHINE_FEATURES', 'mali', 'xf86-video-armsoc xserver-xorg-module-exa', '', d)} \
    xf86-input-evdev \
"
