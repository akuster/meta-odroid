FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-5.4:"


SRC_URI_append = " file://odroid-kmeta;type=kmeta;name=odroid-kmeta;destsuffix=odroid-kmeta"
SRC_URI_append = " file://odroid/odroid.scc"

KERNEL_FEATURES_append_odroid-c2 = "${@bb.utils.contains('MACHINE_FEATURES', 'lima', 'features/drm/drm.scc', '', d)}"

COMPATIBLE_MACHINE_odroid-c1 = "odroid-c1"
COMPATIBLE_MACHINE_odroid-c2 = "odroid-c2"
COMPATIBLE_MACHINE_odroid-xu3 = "odroid-xu3"
COMPATIBLE_MACHINE_odroid-xu4 = "odroid-xu4"
COMPATIBLE_MACHINE_odroid-xu3-lite = "odroid-xu3-lite"
COMPATIBLE_MACHINE_odroid-hc1 = "odroid-hc1"
COMPATIBLE_MACHINE_odroid-h2 = "odroid-h2"
COMPATIBLE_MACHINE_odroid-n2 = "odroid-n2"
