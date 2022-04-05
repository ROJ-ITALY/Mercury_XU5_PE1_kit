FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI += " file://si5338.patch"
SRC_URI += " file://qspi.patch"

SRC_URI += " file://0001-patch-5.4.3-rt1.patch"
SRC_URI += " file://preempt-rt.cfg"
