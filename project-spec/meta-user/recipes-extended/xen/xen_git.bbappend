# Add patch to get PV drivers working, including PV network
# Enable DEBUG HYPERCALL and COLORING in config file

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://0001-fix-pv-drivers.patch \
				   file://defconfig"
