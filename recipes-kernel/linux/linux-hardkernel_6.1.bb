FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.1:"

KBRANCH = "odroidg12-6.1.y"
KBUILD_DEFCONFIG = "odroid_defconfig"

SRCREV = "6bd1ff4f0068268f8150bbcdc9fd6d57929d5fbf"
LINUX_VERSION ?= "6.1.61"

SRC_URI += "\
    file://oe.scc \
"
require linux-hardkernel.inc
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

KERNEL_EXTRA_FEATURES = ""

do_install:prepend() {
    bbnote "custom kernel_do_install customization"
    cp ${B}/arch/arm64/boot/dts/amlogic/*.dtb ${B}/arch/arm64/boot/dts/
}

COMPATIBLE_MACHINE = "(odroid-n2-hardkernel|odroid-c4-hardkernel)"
