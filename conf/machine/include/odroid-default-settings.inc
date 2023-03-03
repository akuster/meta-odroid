# Odroid BSP default settings
SOC_FAMILY .= ":odroid"

PREFERRED_PROVIDER_virtual/xserver ?= "xserver-xorg"
XSERVER = " \
    xserver-xorg \
    xf86-video-fbdev \
    xf86-input-evdev \
    xf86-input-mouse \
    xf86-video-modesetting \
    xserver-xorg-module-libint10 \
"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto"
PREFERRED_VERSION_linux-yocto ?= "6.1%"
LINUX_VERSION_EXTENSION ?= "-odroid"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

MACHINE_FEATURES:append = " serial usbgadget usbhost ext2"

MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

IMAGE_FSTYPES ?= " ext4 wic wic.xz"
