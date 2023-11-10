FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-4.9:"

KBRANCH = "odroidg12-4.9.y"
KBUILD_DEFCONFIG = "odroidg12_defconfig"

SRCREV = "19b39b20ef782796cb9f1bb3a05de49d5541139c"
LINUX_VERSION ?= "4.9.337"

EXTRA_OEMAKE:append = " KBUILD=${B}"
require linux-hardkernel.inc
SRC_URI:append = " file://oe.scc"
KERNEL_FEATURES:remove = "cfg/fs/vfat.scc"

do_install:prepend() {
    bbnote "custom kernel_do_install customization"
    cp ${B}/arch/arm64/boot/dts/amlogic/*.dtb ${B}/arch/arm64/boot/dts/
}

COMPATIBLE_MACHINE = "(odroid-n2-hardkernel|odroid-c4-hardkernel|odroid-hc4-hardkernel)"
