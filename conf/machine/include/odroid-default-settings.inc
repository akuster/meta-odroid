# Odroid BSP default settings
SOC_FAMILY .= ":odroid"

PREFERRED_PROVIDER_virtual/xserver ?= "xserver-xorg"
XSERVER = " \
    xserver-xorg \
    xf86-video-fbdev \
    xf86-input-evdev \
    xf86-input-mouse \
    xf86-input-keyboard \
    xf86-video-modesetting \
    xserver-xorg-module-libint10 \
"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto"
PREFERRED_VERSION_linux-yocto ?= "5.10%"
LINUX_VERSION_EXTENSION ?= "-odroid"

# Do not update fstab file when using wic images
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

# wic default support
WKS_FILE_DEPENDS ?= " \
    e2fsprogs-native \
    bmap-tools-native \
"

MACHINE_FEATURES:append = " serial usbgadget usbhost vfat ext2"

MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

IMAGE_INSTALL:append = " dosfstools mtools kernel-image kernel-modules"

KERNEL_EXTRA_FEATURES ?= "cfg/fs/vfat.scc"
KERNEL_FEATURES:append = " ${KERNEL_EXTRA_FEATURES}"

IMAGE_FSTYPES ?= " ext4 wic wic.xz"
