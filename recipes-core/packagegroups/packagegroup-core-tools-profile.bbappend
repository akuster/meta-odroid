FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# perf fails to build with 4.9 kernel
PERF_odroid-c4-hardkernel = ""
