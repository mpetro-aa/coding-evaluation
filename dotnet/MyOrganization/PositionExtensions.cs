using System;
namespace MyOrganization
{
	/// <summary>
	/// Extension methos for <see cref="Position"/> class.
	/// </summary>
	internal static class PositionExtensions
	{
		/// <summary>
		/// Flatten a Position tree into a list of references.
		/// </summary>
		/// <param name="pos"><see cref="Position"/> node to flatten.</param>
		/// <returns>Return the node itself and all descendent Position node as a flat list.</returns>
		internal static IEnumerable<Position> Flatten(this Position pos)
		{
			yield return pos;
			foreach (var report in pos.GetDirectReports())
			{
				foreach (var r in report.Flatten())
				{
					yield return r;
				}
			}
		}
	}
}

