# Add patch to get PV drivers working, including PV network
# Enable DEBUG HYPERCALL and COLORING in config file

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://0001-fix-pv-drivers.patch \
				   file://0001-xen-RCU-bootparam-to-force-quiescence-at-every-call.patch \
				   file://0002-xen-rename-RCU-idle-timer-and-cpumask.patch \
				   file://0003-xen-deal-with-vCPUs-that-do-not-yield-when-idle.patch \
				   file://0004-xen-remove-ASSERT-in-rcu_quiet_enter.patch \
				   file://defconfig"
