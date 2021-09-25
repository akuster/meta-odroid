FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-5.14:"


SRC_URI:append = " file://odroid-kmeta;type=kmeta;name=odroid-kmeta;destsuffix=odroid-kmeta"
SRC_URI:append = " file://odroid/odroid.scc"

KERNEL_FEATURES:append:odroid-c2 = "${@bb.utils.contains('MACHINE_FEATURES', 'lima', 'features/drm/drm.scc', '', d)}"

COMPATIBLE_MACHINE:odroid-c1 = "odroid-c1"
COMPATIBLE_MACHINE:odroid-c2 = "odroid-c2"
COMPATIBLE_MACHINE:odroid-xu3 = "odroid-xu3"
COMPATIBLE_MACHINE:odroid-xu4 = "odroid-xu4"
COMPATIBLE_MACHINE:odroid-xu3-lite = "odroid-xu3-lite"
COMPATIBLE_MACHINE:odroid-hc1 = "odroid-hc1"
COMPATIBLE_MACHINE:odroid-h2 = "odroid-h2"
COMPATIBLE_MACHINE:odroid-n2 = "odroid-n2"
COMPATIBLE_MACHINE:odroid-c4 = "odroid-c4"
COMPATIBLE_MACHINE:odroid-hc4 = "odroid-hc4"
